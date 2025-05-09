<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const router = useRouter()
const route = useRoute()

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

watch(
    () => route.name,
    () => {
      if (route.name === 'WebtoonDetail') return

      const param = route.name === 'WebtoonAll'
          ? 'all'
          : route.params.daysOfWeekValue

      const index = tabs.findIndex(tab => tab.value === param)
      tabIndex.value = index !== -1 ? index : 0
    },
    { immediate: true }
)

watch(tabIndex, (i) => {
  const tabValue = tabs[i].value
  if (tabValue === 'all') {
    router.push({ name: 'WebtoonAll' })
  } else {
    router.push({ name: 'WebtoonDay', params: { daysOfWeekValue: tabValue } })
  }
})
</script>

<template>
  <v-tabs v-model="tabIndex" align-tabs="start" class="week-tabs">
    <v-tab
        v-for="(tab, index) in tabs"
        :key="tab.value"
        :value="index"
    >
      {{ tab.label }}
    </v-tab>
  </v-tabs>
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