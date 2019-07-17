package com.dwolla.examples

class RootExamples : Examples() {
    override val scope = "dwolla.root"

    override fun run() {
        example("get") {
            dwolla.root.get()
        }
    }
}

fun main() {
    RootExamples().run()
}
