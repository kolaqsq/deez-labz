package com.example.hw6

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.database.NodeEntity
import com.example.hw6.database.NodeViewModel
import com.example.hw6.database.NodeViewModelFactory
import com.example.hw6.databinding.AllNodesFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AllNodesFragment : Fragment() {
    private lateinit var binding: AllNodesFragmentBinding
    private val adapter = NodeListAdapter(this::showRelations, null)
    private val nodeViewModel: NodeViewModel by activityViewModels {
        NodeViewModelFactory((activity?.application as HW6).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllNodesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nodeViewModel.allNodes.observe(viewLifecycleOwner) { nodes ->
            // Update the cached copy of the nodes in the adapter.
            nodes?.let {
                adapter.submitList(it)
                adapter.allNodes = nodes
            }
        }

        setupRecycleView()

        val fab = binding.fab
        fab.setOnClickListener {
            // Respond to FAB click
            context?.let { it1 ->
                // Creating edit text
                val constraintLayout = ConstraintLayout(it1)
                val layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                constraintLayout.layoutParams = layoutParams
                constraintLayout.id = View.generateViewId()

                val textInputLayout = TextInputLayout(it1)
                textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
                layoutParams.setMargins(
                    20.toDp(it1),
                    8.toDp(it1),
                    20.toDp(it1),
                    8.toDp(it1)
                )
                textInputLayout.layoutParams = layoutParams
                textInputLayout.hint = "Node value"
                textInputLayout.id = View.generateViewId()
                textInputLayout.tag = "textInputLayoutTag"


                val textInputEditText = TextInputEditText(it1)
                textInputEditText.id = View.generateViewId()
                textInputEditText.tag = "textInputEditTextTag"

                textInputLayout.addView(textInputEditText)

                val constraintSet = ConstraintSet()
                constraintSet.clone(constraintLayout)

                constraintLayout.addView(textInputLayout)

                //Building dialog
                MaterialAlertDialogBuilder(it1)
                    .setTitle(resources.getString(R.string.add_dialog_title))
                    .setMessage(resources.getString(R.string.add_dialog_message))
                    .setView(constraintLayout)
                    .setNegativeButton(resources.getString(R.string.dialog_cancel)) { dialog, which ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(resources.getString(R.string.dialog_add)) { dialog, which ->
                        if (TextUtils.isEmpty(textInputEditText.text) or (textInputEditText.text.toString()
                                .toIntOrNull() == null)
                        ) {
                            Log.d("Value check", "bad")
                        } else {
                            val nodeValue = textInputEditText.text.toString().toInt()
                            Log.d("Value check", nodeValue.toString())
                            val node =
                                NodeEntity(0, textInputEditText.text.toString().toInt(), listOf())
                            nodeViewModel.insert(node)
                        }
                    }
                    .show()
            }
        }
    }

    private fun setupRecycleView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun Int.toDp(context: Context): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
    ).toInt()

    private fun showRelations(node: NodeEntity, trigger: String): Unit {
        when (trigger) {
            "relations" -> {
                val activityCallBack = requireActivity() as ActivityCallBack
                activityCallBack.editRelations(node)
                return
            }
        }
    }
}