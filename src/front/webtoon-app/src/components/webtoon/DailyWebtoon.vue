<script setup lang="ts">
import {computed, ref, watch} from "vue";
import {useDailyWebtoon} from "@/composables/useDailyWebtoon";
import EntireDailyWebtoonItems from "@/components/webtoon/EntireDailyWebtoonItems.vue";
import RecommendedDailyWebtoonItems from "@/components/webtoon/RecommendedDailyWebtoonItems.vue";

const props = defineProps({
  daysOfWeekIndex: {
    type: Number,
    required: true
  }
})

const {updateDayOfWeek} = useDailyWebtoon()

const daysOfWeek = [
  {name: '월요', value: 0, queryParam: 'mon'},
  {name: '화요', value: 1, queryParam: 'tue'},
  {name: '수요', value: 2, queryParam: 'wed'},
  {name: '목요', value: 3, queryParam: 'thu'},
  {name: '금요', value: 4, queryParam: 'fri'},
  {name: '토요', value: 5, queryParam: 'sat'},
  {name: '일요', value: 6, queryParam: 'sun'}
]

const currentDay = computed(() => daysOfWeek.find(day => day.value === props.daysOfWeekIndex))
const currentDayName = computed(() => currentDay.value?.name ?? '')
const currentDayParam = computed(() => currentDay.value?.queryParam ?? undefined)

function onSort() {
  console.log(selectedSort.value) //TODO api 재호출
}

type SortOption = 'popular' | 'updated' | 'views' | 'rating'

const selectedSort = ref<SortOption>('popular')

const sortOptions = [
  { label: '인기순', value: 'popular' },
  { label: '업데이트순', value: 'updated' },
  { label: '조회순', value: 'views' },
  { label: '별점순', value: 'rating' }
] satisfies readonly { label: string; value: SortOption }[]

watch([currentDayParam], ([newDay]) => {
  if (newDay) {
    updateDayOfWeek(newDay)
  }
}, {immediate: true})

</script>

<template>
  <v-container fluid class="pt-1 px-0">
    <v-row>
      <v-col class="mt-2" cols="12">
        <v-card elevation="0">
          <v-card-title class="font-weight-bold">추천 {{ currentDayName }}웹툰</v-card-title>
          <v-card-item>
            <RecommendedDailyWebtoonItems/>
          </v-card-item>
        </v-card>
      </v-col>
    </v-row>
    <v-row class="mt-10">
      <v-col cols="12">
        <v-card elevation="0">
          <v-card-title class="font-weight-bold d-flex justify-space-between align-center">
          <span>전체 {{ currentDayName }}웹툰</span>
          <v-btn-toggle
              v-model="selectedSort"
              dense
              class="sort-options"
              background-color="transparent"
              mandatory
          >
            <v-btn
                v-for="option in sortOptions"
                :key="option.value"
                :value="option.value"
                @click="onSort"
                small
                class="pa-1"
            >
              {{ option.label }}
            </v-btn>
          </v-btn-toggle>
          </v-card-title>
          <v-card-item>
            <EntireDailyWebtoonItems/>
          </v-card-item>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>