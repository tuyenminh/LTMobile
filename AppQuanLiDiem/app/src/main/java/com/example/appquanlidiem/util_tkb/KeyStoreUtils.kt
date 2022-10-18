package com.example.appquanlidiem.util_tkb

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.nio.ByteBuffer
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

object KeyStoreUtils {
    private const val alias = "LightTimetable"
    private const val transformation = "AES/GCM/NoPadding"
    private const val provider = "AndroidKeyStore"


    @JvmStatic
    fun getKey(): SecretKey {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val keyStore = KeyStore.getInstance(provider)
            keyStore.load(null)
            val key = keyStore.getKey(alias, null)
            return if (key == null) {

                val keyGenerator = KeyGenerator.getInstance(
                        KeyProperties.KEY_ALGORITHM_AES, provider)
                keyGenerator.init(KeyGenParameterSpec
                        .Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                        .build())

                keyGenerator.generateKey()
            } else {
                key as SecretKey
            }
        } else {

            return SecretKeySpec(Build.FINGERPRINT.toByteArray().sliceArray(0..15), "AES")
        }
    }


    @JvmStatic
    fun encrypt(data: String?): String {
        if (data == null || data.isEmpty()) {
            return data.orEmpty()
        }
        val cipher = Cipher.getInstance(transformation)
        val key = getKey()

        cipher.init(Cipher.ENCRYPT_MODE, key)
        val iv = cipher.iv
        val cipherText = cipher.doFinal(data.toByteArray())


        val buffer = ByteBuffer.allocate(Int.SIZE_BYTES + iv.size + cipherText.size)
        buffer.putInt(iv.size)
        buffer.put(iv)
        buffer.put(cipherText)

        return bytesToBase64(buffer.array())
    }


    @JvmStatic
    fun decrypt(data: String?): String {
        if (data == null || data.isEmpty()) {
            return data.orEmpty()
        }
        val cipher = Cipher.getInstance(transformation)
        val key = getKey()
        val cipherMsg = base64ToBytes(data)


        val buffer = ByteBuffer.wrap(cipherMsg)
        val ivSize = buffer.int
        val iv = ByteArray(ivSize)
        buffer.get(iv)
        val cipherText = ByteArray(buffer.remaining())
        buffer.get(cipherText)


        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))
        return String(cipher.doFinal(cipherText))
    }


    @JvmStatic
    fun bytesToBase64(data: ByteArray): String {
        return Base64.encodeToString(data, Base64.DEFAULT)
    }


    @JvmStatic
    fun base64ToBytes(base64: String): ByteArray {
        return Base64.decode(base64, Base64.DEFAULT)
    }
}