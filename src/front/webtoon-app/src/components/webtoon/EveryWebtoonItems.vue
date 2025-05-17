<script setup lang="ts">
import {computed} from "vue";
import {useWebtoons} from "@/composables/useWebtoons";
import {navigateToWebtoonDetail} from "@/utils/navigation";

const daysOfWeek = [
  {name: 'mon', value: '월요'},
  {name: 'tue', value: '화요'},
  {name: 'wed', value: '수요'},
  {name: 'thu', value: '목요'},
  {name: 'fri', value: '금요'},
  {name: 'sat', value: '토요'},
  {name: 'sun', value: '일요'}
]

const {data} = useWebtoons()

const orderedColumnsData = computed(() => {
  return daysOfWeek.map(day => ({
    key: day.name,
    label: day.value,
    items: data.value?.[day.name] || []
  }));
});

const maxRowCount = computed(() => {
  const lengths = orderedColumnsData.value.map(col => col.items.length);
  return lengths.length > 0 ? Math.max(...lengths) : 0;
});
</script>

<template>
  <template v-if="orderedColumnsData.some(col => col.items.length > 0)">
    <v-row class="flex-wrap pa-0 ma-0">
      <v-col
          v-for="(colItems, colIndex) in orderedColumnsData"
          :key="colIndex"
          cols="auto"
          class="pa-0 monthly-webtoon-col mb-1"
      >
        <v-card class="card-header mt-2" elevation="0">
          <v-card-item class="pa-1 text-center font-weight-bold text-body-2">
            {{ colItems.label }}웹툰
          </v-card-item>
        </v-card>

        <v-card
            class="card-item mt-3 mb-3"
            elevation="0"
            v-for="(item, rowIndex) in maxRowCount"
            :key="rowIndex"
        >
          <v-card-item class="pa-1" v-if="colItems.items[rowIndex]">
            <v-img
                class="with-border"
                height="100%"
                cover
                :src="colItems.items[rowIndex]?.thumbnail"
                @click="navigateToWebtoonDetail(colItems.items[rowIndex].id, 'tab을 넣어야합니다.....')"
            />
          </v-card-item>
          <v-card-text class="pa-1 font-weight-medium ellipsis">
            {{ colItems.items[rowIndex]?.title ?? '' }}
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </template>
</template>
<style scoped>
.monthly-webtoon-col {
  flex: 0 0 calc(100% / 7);
  max-width: calc(100% / 7);
  padding: 0;
  border-top: 1px solid #e0e0e0;
  border-bottom: 1px solid #e0e0e0;
  border-right: 1px solid #e0e0e0;
}

.monthly-webtoon-col:first-child {
  border-left: 1px solid #e0e0e0;
}

.ellipsis {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>