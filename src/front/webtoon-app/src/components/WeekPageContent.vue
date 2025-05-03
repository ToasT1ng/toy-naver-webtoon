<script setup lang="ts">
import {computed, ref} from 'vue'
import MonthlyWebtoon from '@/components/MonthlyWebtoon.vue'
import DailyWebtoon from '@/components/DailyWebtoon.vue'

const tabs = [
  { label: '전체', value: 'all' },
  { label: '월', value: 'mon' },
  { label: '화', value: 'tue' },
  { label: '수', value: 'wed' },
  { label: '목', value: 'thu' },
  { label: '금', value: 'fri' },
  { label: '토', value: 'sat' },
  { label: '일', value: 'sun' },
]

const tabIndex = ref(0)

const currentComponent = computed(() =>
    tabIndex.value === 0 ? MonthlyWebtoon : DailyWebtoon
)

const daysOfWeek = computed(() => tabs[tabIndex.value].value)
</script>

<template>
  <v-container fluid class="pa-0">
    <v-tabs v-model="tabIndex" align-tabs="start" class="week-tabs">
      <v-tab v-for="(tab, index) in tabs" :key="tab.value" :value="index">
        {{ tab.label }}
      </v-tab>
    </v-tabs>

    <component :is="currentComponent" :days-of-week="daysOfWeek"/>
  </v-container>
</template>

<style scoped>
.week-tabs {
  border: 1px solid #ccc;
}

.v-tab {
  color: #555 !important;
  font-weight: 500;
  margin-right: 4px !important;
  min-width: unset !important;
}

.v-tab--selected {
  color: #000 !important;
  border-bottom: 1px solid #000;
}

.v-tabs {
  --v-tabs-selected-border-color: transparent;
  --v-tabs-selected-text-color: #000;
}

.v-window-item {
  transition: none !important;
  animation: none !important;
}
</style>