package org.zchzh.spel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.junit.jupiter.api.Assertions.*;



@Slf4j
class SpELApplicationTest {


    private final ExpressionParser parser = new SpelExpressionParser();

    @Data
    static class User {

        private String username;

        private String password;
    }

    @Test
    void simpleUse() {
        String username = "testuser";
        Expression expression = parser.parseExpression("username");
        User user = new User();
        user.setUsername(username);
        EvaluationContext context = new StandardEvaluationContext(user);
        Assertions.assertEquals(expression.getValue(context, String.class), username);
    }


    @Test
    void operation() {
        Expression expression = parser.parseExpression("1+2");
        Assertions.assertEquals(expression.getValue(), 3);
    }

    @Test
    void stringConcat() {
        Expression expression = parser.parseExpression("'hello'.concat(' world')");
        Assertions.assertEquals(expression.getValue(), "hello world");
    }



}