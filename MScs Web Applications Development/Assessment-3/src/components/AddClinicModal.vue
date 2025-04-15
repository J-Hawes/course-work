<template>
  <div
    class="modal fade"
    id="addClinicModal"
    tabindex="-1"
    aria-labelledby="addClinicModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addClinicModalLabel">Add New Clinic</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <EditClinicForm :clinic="newClinic" @save="addClinic" @clear="clearNewClinic" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import EditClinicForm from './EditClinicForm.vue'

defineProps(['newClinic'])
const emit = defineEmits(['add', 'clear'])

const addClinic = (clinic) => {
  emit('add', clinic)
}

const clearNewClinic = (clinic) => {
  emit('clear', clinic)
}
</script>
