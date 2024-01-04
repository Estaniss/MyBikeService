package br.edu.ifsp.aluno.mybikeservice.view.adapter

interface OnMaintenanceClickListener {
    fun onMaintenanceClick(position: Int)
    fun onRemoveMaintenanceMenuItemClick(position: Int)
    fun onEditMaintenanceMenuItemClick(position: Int)
    fun onDoneCheckBoxClick(position: Int, checked: Boolean)
}