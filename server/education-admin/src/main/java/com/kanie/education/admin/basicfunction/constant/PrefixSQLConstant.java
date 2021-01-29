package com.kanie.education.admin.basicfunction.constant;

import com.kanie.education.admin.basicfunction.po.UmRole;
import com.kanie.education.admin.basicfunction.po.UmUser;

/**
 * SQL 前缀常量类
 * Created by kanie on 2021/1/28 16:50
 */
public class PrefixSQLConstant {

    /**
     * user
     */
    public static final String SQL_PREFIX_ID_UM_USER = UmUser.class.getName() + ".";

    /**
     * role
     */
    public static final String SQL_PREFIX_ID_UM_ROLE = UmRole.class.getName() + ".";
}
