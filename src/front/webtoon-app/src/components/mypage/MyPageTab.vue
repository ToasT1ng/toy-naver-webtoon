<script setup lang="ts">
import {MyPageTab} from "@/types/common";
import router from "@/router";

interface ITab {
  label: string
  value: MyPageTab
}

const tabs = [
  { label: '관심웹툰', value: 'likes' },
  { label: '최근 본', value: 'history' },
] satisfies ITab[]

const onTabClick = (value: MyPageTab) => {
  const newRoute =
      value === 'likes'
          ? { name: 'LikedWebtoons' }
          : { name: 'ViewHistory' }

  router.push(newRoute)
}
</script>

<template>
  <v-tabs align-tabs="start" class="my-page-tabs">
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
.my-page-tabs {
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