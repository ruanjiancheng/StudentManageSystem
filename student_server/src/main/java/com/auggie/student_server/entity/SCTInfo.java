package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/11 11:14
 * @Description: SCTInfo
 * @Version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("SCTInfo")
public class SCTInfo {
    private Integer sid;
    private Integer tid;
    private Integer cid;
    private String sname;
    private String tname;
    private String cname;
    private Float grade;
    private String term;
}
