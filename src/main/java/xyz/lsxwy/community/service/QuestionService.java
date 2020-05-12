package xyz.lsxwy.community.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lsxwy.community.dto.PaginationDTO;
import xyz.lsxwy.community.dto.QuestionDTO;
import xyz.lsxwy.community.exception.CustomizeErrorCode;
import xyz.lsxwy.community.exception.CustomizeException;
import xyz.lsxwy.community.mapper.QuestionExtMapper;
import xyz.lsxwy.community.mapper.QuestionMapper;
import xyz.lsxwy.community.mapper.UserMapper;
import xyz.lsxwy.community.model.Question;
import xyz.lsxwy.community.model.QuestionExample;
import xyz.lsxwy.community.model.User;
import xyz.lsxwy.community.model.UserExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);

        page = paginationDTO.getPage();
        Integer offset = size * (page - 1);

        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<QuestionDTO>();

        for (Question question : questions) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(users.get(0));
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }

    public PaginationDTO list(String creator, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(creator);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);

        page = paginationDTO.getPage();
        Integer offset = size * (page - 1);


        QuestionExample questionExample2 = new QuestionExample();
        questionExample2.createCriteria().andCreatorEqualTo(creator);
        questionExample2.setOrderByClause("gmt_create desc");

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample2, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<QuestionDTO>();

        for (Question question : questions) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(users.get(0));
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
        questionDTO.setUser(users.get(0));
        return questionDTO;
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        } else {
            String[] tags = StringUtils.split(queryDTO.getTag(), ",");
            String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
            Question question = new Question();
            question.setId(queryDTO.getId());
            question.setTag(regexpTag);

            List<Question> questions = questionExtMapper.selectRelated(question);
            List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(q,questionDTO);
                return questionDTO;
            }).collect(Collectors.toList());
            return questionDTOS;
        }
    }
}
