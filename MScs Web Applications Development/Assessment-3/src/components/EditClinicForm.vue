<template>
  <div>
    <div class="floating-label" v-for="(value, key) in localClinic" :key="key">
      <input
        :type="key === 'phone' ? 'tel' : 'text'"
        :id="key"
        class="form-control mb-2"
        v-model="localClinic[key]"
        placeholder=" "
      />
      <label :for="key">{{ key.charAt(0).toUpperCase() + key.slice(1) }}</label>
    </div>
    <div class="d-flex justify-content-end">
      <button
        class="btn btn-secondary btn-sm me-2"
        data-bs-dismiss="modal"
        @click="$emit('clear', localClinic)"
      >
        Cancel
      </button>
      <button class="btn btn-success btn-sm me-2" @click="$emit('save', localClinic)">Save</button>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

const props = defineProps(['clinic'])
defineEmits(['save', 'clear'])
const localClinic = reactive({ ...props.clinic })
</script>

<style scoped>
.floating-label {
  position: relative;
  margin-bottom: 1rem;
}

.floating-label input {
  width: 100%;
  padding: 0.75rem 0.75rem 0.25rem;
  font-size: 1rem;
  border: 1px solid #ced4da;
  border-radius: 4px;
  box-sizing: border-box;
}

.floating-label label {
  position: absolute;
  top: 50%;
  left: 0.75rem;
  transform: translateY(-50%);
  font-size: 1rem;
  color: #6c757d;
  pointer-events: none;
  transition: all 0.2s ease-in-out;
}

.floating-label input:focus + label,
.floating-label input:not(:placeholder-shown) + label {
  top: 0.25rem;
  font-size: 0.75rem;
  color: #495057;
}

.floating-label input:focus {
  outline: none;
  border-color: #80bdff;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}
</style>
