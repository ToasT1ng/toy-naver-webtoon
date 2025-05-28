<script setup lang="ts">
import {useRouter} from 'vue-router'
import {WeekPageTab} from "@/types/common";
import {useWebtoonStore} from "@/stores/webtoonStore";

const router = useRouter()

interface ITab {
  label: string
  value: WeekPageTab
}

const tabs = [
  { label: '전체', value: 'all' },
  { label: '월', value: 'mon' },
  { label: '화', value: 'tue' },
  { label: '수', value: 'wed' },
  { label: '목', value: 'thu' },
  { label: '금', value: 'fri' },
  { label: '토', value: 'sat' },
  { label: '일', value: 'sun' },
] satisfies ITab[]

const webtoonStore = useWebtoonStore()

const onTabClick = (value: WeekPageTab) => {
  webtoonStore.tab = value

  const newRoute =
      value === 'all'
          ? { name: 'WebtoonAll' }
          : { name: 'WebtoonDay', params: { daysOfWeekValue: value } }

  router.push(newRoute)
}
</script>

<template>
  <v-tabs v-model="webtoonStore.tab" align-tabs="start" class="week-tabs">
    <v-tab
        v-for="tab in tabs"
        :key="tab.value"
        :value="tab.value"
        @click="onTabClick(tab.value)"
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