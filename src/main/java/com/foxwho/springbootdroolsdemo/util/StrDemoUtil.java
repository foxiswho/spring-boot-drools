package com.foxwho.springbootdroolsdemo.util;

import cn.hutool.core.util.StrUtil;

public class StrDemoUtil {
    public static boolean containsStr(CharSequence str, CharSequence searchStr) {
        return StrUtil.contains(str, searchStr);
    }
}
