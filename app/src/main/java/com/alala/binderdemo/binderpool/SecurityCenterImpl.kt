package com.alala.binderdemo.binderpool

class SecurityCenterImpl : ISecurityCenter.Stub() {

    private val SECURITY_CODE: Char = '^'
    override fun encrypt(content: String?): String {

        val charArray = content?.toCharArray()
        charArray?.forEachIndexed { index, c ->
            charArray[index] = (SECURITY_CODE.code xor c.code).toChar()
        }
        return charArray.toString()

    }

    override fun decrypt(password: String?): String {
        return encrypt(password)
    }
}