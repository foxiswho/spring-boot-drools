package com.foxwho.springbootdroolsdemo.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@JsonSerialize
@Data
public class WrapperDrools<E> implements Serializable {
    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 4893280118017319089L;

    /**
     * 成功码.
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 成功信息.
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 错误码.
     */
    public static final int ERROR_CODE = 500;

    /**
     * 错误信息.
     */
    public static final String ERROR_MESSAGE = "内部异常";

    /**
     * 错误码：参数非法
     */
    public static final int ILLEGAL_ARGUMENT_CODE_ = 100;

    /**
     * 错误信息：参数非法
     */
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";

    /**
     * 编号.
     */
    private int code;

    /**
     * 信息.
     */
    private String message;

    /**
     * 结果数据
     */
    private E data;

    /**
     * code=200
     */
    public WrapperDrools() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     *
     *
     * @param code    the code
     * @param message the message
     */
    public WrapperDrools(int code, String message) {
        this(code, message, null);
    }

    /**
     *
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     */
    public WrapperDrools(int code, String message, E data) {
        super();
        this.code(code).message(message).data(data);
    }

    /**
     * Sets the 编号 , 返回自身的引用.
     *
     * @param code the new 编号
     * @return the wrapper
     */
    private WrapperDrools<E> code(int code) {
        this.setCode(code);
        return this;
    }

    /**
     * Sets the 信息 , 返回自身的引用.
     *
     * @param message the new 信息
     * @return the wrapper
     */
    private WrapperDrools<E> message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param data the new 结果数据
     * @return the wrapper
     */
    public WrapperDrools<E> data(E data) {
        this.setData(data);
        return this;
    }

    /**
     *
     */
    public void ok() {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
    }

    public void ok(String error) {
        this.code = SUCCESS_CODE;
        this.message = error;
    }

    public void ok(String error, E data) {
        this.code = SUCCESS_CODE;
        this.message = error;
        this.setData(data);
    }

    /**
     *
     */
    public void error() {
        this.code = ERROR_CODE;
        this.message = ERROR_MESSAGE;
    }

    public void error(String error) {
        this.code = ERROR_CODE;
        this.message = error;
    }

    public void error(String error, E data) {
        this.code = ERROR_CODE;
        this.message = error;
        this.setData(data);
    }

    public void wrap(int code, String message, E data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public void wrap(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public void wrap(int code) {
        this.setCode(code);
        this.setMessage("");
    }

    /**
     * 判断
     *
     * @return
     */
    @JsonIgnore
    public boolean isOk() {
        return this.code == SUCCESS_CODE;
    }

    /**
     * 判断
     *
     * @return
     */
    @JsonIgnore
    public boolean isError() {
        return this.code != SUCCESS_CODE;
    }
}
