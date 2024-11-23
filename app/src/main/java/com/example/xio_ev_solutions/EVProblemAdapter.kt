package com.example.xio_ev_solutions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class EVProblemAdapter(
    private var problemsList: List<EVProblem>,
    private val onReferenceClick: (String) -> Unit
) : RecyclerView.Adapter<EVProblemAdapter.ProblemViewHolder>() {

    inner class ProblemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val problemText: TextView = itemView.findViewById(R.id.problemText)
        val solutionText: TextView = itemView.findViewById(R.id.solutionText)
        val referenceText: TextView = itemView.findViewById(R.id.referenceText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ev_problem, parent, false)
        return ProblemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        val problem = problemsList[position]
        holder.problemText.text = problem.problem
        holder.solutionText.text = "Solution: ${problem.solution}"
        holder.referenceText.text = "Reference: ${problem.reference}"
        holder.referenceText.setOnClickListener {
            onReferenceClick(problem.reference)
        }
    }

    override fun getItemCount() = problemsList.size

    fun updateList(newList: List<EVProblem>) {
        problemsList = newList
        notifyDataSetChanged()
    }
}
