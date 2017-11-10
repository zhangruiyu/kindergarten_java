package com.zhangruiyu.github.youeryuanxiaozhushou

import java.util.ArrayList
import java.util.Arrays
import java.util.Comparator

/**
 * NextInputs
 *
 * @author 陈小锅 (yoojia.chen@gmail.com)
 */
open class NextInputs {

    private val mInputSpecs = ArrayList<InputSpec>()

    private var mMessageDisplay: MessageDisplay = MessageDisplay { input, message -> System.err.println("TEST FAIL: " + message) }

    /**
     * 默认情况下，校验测试失败即停止其它校验
     */
    private var mStopIfFail = true

    /**
     * 执行校验测试，并返回测试结果。
     * @return 校验测试结果是否成功
     */
    fun test(): Boolean {
        if (mInputSpecs.isEmpty()) {
            throw IllegalArgumentException("No inputs and schemes to test")
        }
        var working: InputSpec? = null
        var passed = true
        for (spec in mInputSpecs) {
            working = spec
            val r = perform(spec)
            if (!r.passed) {
                mMessageDisplay.show(working.input, r.message)
                passed = false
                if (mStopIfFail) {
                    return false
                }
            }
        }
        return passed

    }

    /**
     * 添加输入条目及测试模式。
     * @param input 输入条目
     * @param schemes 测试模式
     * @return NextInputs
     */
    fun add(input: Input, vararg schemes: Scheme): NextInputs {
        if (schemes == null || schemes.size == 0) {
            throw IllegalArgumentException("Test schemes is required !")
        }
        Arrays.sort(schemes, ORDERING)
        mInputSpecs.add(InputSpec(input, schemes))
        return this
    }

    /**
     * 移除指定Input的校验条目
     * @param input Input对象
     * @return NextInputs
     */
    fun remove(input: Input): NextInputs {
        val toRemove = ArrayList<InputSpec>(1)
        for (spec in mInputSpecs) {
            if (spec.input === input) {
                toRemove.add(spec)
            }
        }
        mInputSpecs.removeAll(toRemove)
        return this
    }

    /**
     * 清除所有校验条目
     * @return NextInputs
     */
    fun clear(): NextInputs {
        mInputSpecs.clear()
        return this
    }

    /**
     * 在校验测试遇到失败时，是否停止校验
     * @param stopOnFail 是否停止
     * @return NextInputs
     */
    fun setStopIfFail(stopOnFail: Boolean): NextInputs {
        mStopIfFail = stopOnFail
        return this
    }

    /**
     * 设置校验测试结果消息显示接口
     * @param display 消息显示接口。
     * @throws NullPointerException 当参数为Null时，抛出异常。
     * @return NextInputs
     */
    fun setMessageDisplay(display: MessageDisplay?): NextInputs {
        if (display == null) {
            throw NullPointerException("MessageDisplay is null !")
        }
        mMessageDisplay = display
        return this
    }

    /**
     * 流式API
     * @param input Input对象
     * @return 流式API接口
     */
    fun add(input: Input): Fluent {
        return Fluent(input, this)
    }

    companion object {

        private val ORDERING = Comparator<Scheme> { lhs, rhs -> lhs.priority - rhs.priority }
        const val CUSTOM_PARAMS_VALIDATE: String = "CUSTOM_PARAMS_VALIDATE"
        @Throws(Exception::class)
        private fun perform(spec: InputSpec): Result {
            val value = spec.input.value
            for (scheme in spec.schemes) {
                if (!scheme.verifier.perform(value)) {
                    val message: String
                    if (scheme.verifier is SingleVerifier<*>) {
                        message = formatTplMessage(scheme.message,
                                scheme.verifier.benchmarkValueForMessage())
                    } else if (scheme.verifier is PairedVerifier<*>) {
                        val v = scheme.verifier
                        message = formatTplMessage(scheme.message,
                                v.benchmark1stValueForMessage(),
                                v.benchmark2ndValueForMessage())
                    } else {
                        message = scheme.message
                    }
                    return Result(false, message)
                }
            }
            return Result(true, "PASSED")
        }

        private fun formatTplMessage(message: String, vararg args: Any): String {
            var output = message
            for (i in args.indices) {
                output = output.replace("{$i}", args[i].toString())
            }
            return output
        }

    }
}
