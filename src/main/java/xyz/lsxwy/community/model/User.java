package xyz.lsxwy.community.model;

import lombok.Data;
/**描述: @Datahui:作用于类上，是以下注解的集合：@ToString @EqualsAndHashCode @Getter @Setter @RequiredArgsConstructor*/
@Data
public class User {
    private String name;
    private String account_id;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String bio;
    private String avatar_url;
}
