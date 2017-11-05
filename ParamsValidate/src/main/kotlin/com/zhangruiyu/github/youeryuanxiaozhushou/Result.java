package com.zhangruiyu.github.youeryuanxiaozhushou;

final class Result {
    public final boolean passed;
    public final String message;

    public Result(boolean passed, String message) {
        this.passed = passed;
        this.message = message;
    }
}