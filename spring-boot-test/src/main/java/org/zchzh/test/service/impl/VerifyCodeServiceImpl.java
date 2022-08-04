package org.zchzh.test.service.impl;

import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zchzh.test.service.VerifyCodeService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author zengchzh
 * @date 2022/8/4
 */

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private final List<Supplier<Character>> supplierList = new ArrayList<Supplier<Character>>() {{
        add(() -> RandomUtil.randomChar(RandomUtil.BASE_CHAR));
        add(() -> RandomUtil.randomChar(RandomUtil.BASE_NUMBER));
    }};

    private static final int CODE_LEN = 6;

    private final Set<String> codeSet = new HashSet<>();

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder(CODE_LEN);
        IntStream.range(0, CODE_LEN).forEach(i -> {
            sb.append(supplierList.get(RandomUtil.randomInt(0, supplierList.size())).get());
        });
        String code = sb.toString();
        if (codeSet.contains(code)) {
            code = getCode();
        }
        codeSet.add(code);
        return code;
    }

    @Override
    public Boolean verify(String code) {
        return !StringUtils.isEmpty(code) && codeSet.contains(code);
    }
}
