/*
 * Created by Coffee IT
 *
 * MIT License
 *
 * Copyright (c) 2022 Coffee IT
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package nl.coffeeit.aroma.emojipicker.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import nl.coffeeit.aroma.emojipicker.presentation.ui.emoji.EmojiBottomSheet
import nl.coffeeit.aroma.emojipicker.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var emojiBottomSheetDialogFragment: BottomSheetDialogFragment? = null

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setEmojiPicker()
        setListener()
    }

    private fun setEmojiPicker() {
        emojiBottomSheetDialogFragment = EmojiBottomSheet.newInstance({ emoji ->
            binding.emoji = emoji.emoji
            emojiBottomSheetDialogFragment?.dismiss()
        })
    }

    private fun setListener() {
        binding.actionShow.setOnClickListener {
             val existingFragment =
            supportFragmentManager.findFragmentByTag(EmojiBottomSheet.TAG) as EmojiBottomSheet?

        if (existingFragment != null && existingFragment.isAdded) {
            // Fragment is already added, dismiss it
            existingFragment.dismiss()
        } else {
            // Fragment is not added or null, show the new fragment
            emojiBottomSheetDialogFragment?.show(supportFragmentManager, EmojiBottomSheet.TAG)
        }
        }
    }
}
