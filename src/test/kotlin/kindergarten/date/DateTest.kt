package kindergarten.date

import kindergarten.utils.DateUtils


fun main(args: Array<String>) {
    var a = DateUtils.isBelong("10:09:00", "15:45:59")
    print(a)
}