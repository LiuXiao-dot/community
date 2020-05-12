package xyz.lsxwy.community.cache;

import org.apache.commons.lang3.StringUtils;
import xyz.lsxwy.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("C","C++"));
        tagDTOS.add(program);

        TagDTO software = new TagDTO();
        software.setCategoryName("软件技能");
        software.setTags(Arrays.asList("protues","Labview","Keil uVision","CAD","Visual Studio","VC++"));
        tagDTOS.add(software);

        TagDTO others = new TagDTO();
        others.setCategoryName("其他");
        others.setTags(Arrays.asList("光学","工程制图","数字电路","模拟电路","嵌入式"));
        tagDTOS.add(others);

        return tagDTOS;
    }

    public static String filterValid(String tags)
    {
        String[] split = StringUtils.split(tags,",");
        List<TagDTO> tagDTOs = get();

        List<String> tagList = tagDTOs.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());

       String invalid = Arrays.stream(split).filter((t -> !tagList.contains(t))).collect(Collectors.joining(","));

       return invalid;
    }
}
