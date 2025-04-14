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
      <button class="btn btn-success btn-sm me-2" @click="$emit('saveEdit', clinic)">Save</button>
      <button class="btn btn-secondary btn-sm" @click="$emit('cancelEdit')">Cancel</button>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

const props = defineProps(['clinic'])
defineEmits(['saveEdit', 'cancelEdit'])
const localClinic = reactive({ ...props.clinic })
</script>
