package com.uc3m.searchyourrecipe.views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentFavRecipesBinding
import com.uc3m.searchyourrecipe.databinding.FragmentShoppingListBinding
import com.uc3m.searchyourrecipe.databinding.PopupAddIngredientBinding

class EditTextDialog : DialogFragment() {

    companion object {
        private const val EXTRA_TITLE = "title"
        private const val EXTRA_HINT = "hint"
        private const val EXTRA_LAYOUT = "layout"

        fun newInstance(title: String? = null, hint: String? = null, layout: Int): EditTextDialog {
            val dialog = EditTextDialog()
            val args = Bundle().apply {
                putString(EXTRA_TITLE, title)
                putString(EXTRA_HINT, hint)
                putInt(EXTRA_LAYOUT, layout)
            }
            dialog.arguments = args
            return dialog
        }
    }

    lateinit var editText: EditText
    var onOk: (() -> Unit)? = null
    var onCancel: (() -> Unit)? = null

    private lateinit var  binding: PopupAddIngredientBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString(EXTRA_TITLE)
        val hint = arguments?.getString(EXTRA_HINT)
        val layout = arguments?.getInt(EXTRA_LAYOUT)
        val view = when (layout) {
            is Int -> {
                requireActivity().layoutInflater.inflate(layout, null)
            }
            else -> null
        }
        println("hasta aqui")


        if (view != null) {
            when (layout) {
               // R.layout.popup_add_ingredient -> editText = view.editText_newList
                //R.layout.fragment_shopping_list -> editText = view.findViewById(getView())
               // binding = PopupAddIngredientBinding.inflate( layoutInflater,  ViewGroup?,false)
               R.layout.popup_add_ingredient -> editText = binding.popupIngredientName
            }
        }
        editText.hint = hint

        val builder = AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setView(view)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    if (!editText.text.isBlank()) {
                        onOk?.invoke()
                    } else {
                        onCancel?.invoke()
                    }
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    onCancel?.invoke()
                }

        return builder.create()
    }
}