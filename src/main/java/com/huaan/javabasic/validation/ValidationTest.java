package com.huaan.javabasic.validation;

public class ValidationTest {
    public static void main(String[] args) {
        PayRequestDO requestDto = new PayRequestDO();
        //requestDto.setPayTime("abc");
        //requestDto.setStatus("999");
        ValidationUtils.validate(requestDto);
    }
}
