package top.xuansu.mirai.zeServerIndicator

import kotlinx.coroutines.*


fun ubAsync() {
    CoroutineScope(Dispatchers.IO).launch {UB.webforub()}
    while (true) {
        while (UB.wsfail) {
            Thread.sleep(7000)
            UB.wsfail = false
            CoroutineScope(Dispatchers.IO).launch {UB.webforub()}
        }
        Thread.sleep(2500)
    }
}

fun zedAsync() {
    CoroutineScope(Dispatchers.IO).launch {
        Zed.webforZED()
        Zed.findOBJ()
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun coroutine() {
    GlobalScope.launch { ubAsync() }
    GlobalScope.launch {
        Zed.webforZED()
        while (true) {
            withContext(Dispatchers.IO) {
                Thread.sleep(15000)
            }
            zedAsync()
        }
    }
    var Time = 0

    GlobalScope.launch {
        while (true) {
            if (Time >= 20) {
                System.gc()
                Time = 0
            }
            delay(60000)
            Time += 1
        }
    }
}