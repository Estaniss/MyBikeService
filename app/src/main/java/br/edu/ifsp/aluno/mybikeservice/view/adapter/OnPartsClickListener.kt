package br.edu.ifsp.aluno.mybikeservice.view.adapter

interface OnPartsClickListener {
    fun onPartClick(position: Int)
    fun onRemovePartMenuItemClick(position: Int)
    fun onEditPartMenuItemClick(position: Int)
}