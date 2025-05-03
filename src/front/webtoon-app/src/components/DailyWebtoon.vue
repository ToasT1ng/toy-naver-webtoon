<script setup lang="ts">
import {computed, watch} from "vue";
import {useDailyWebtoon} from "@/composables/useDailyWebtoon";

const props = defineProps({
  daysOfWeekIndex: {
    type: Number,
    required: true
  }
})

const {updateDayOfWeek, data} = useDailyWebtoon()

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


watch([currentDayParam], ([newDay]) => {
  if (newDay) {
    updateDayOfWeek(newDay)
  }
}, { immediate: true })

</script>

<template>
  <v-container fluid class="pt-1 px-0">
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-card-title>추천 {{ currentDayName }}웹툰</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="4">
                <v-card>
                  <v-card-text>웹툰 1</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="4">
                <v-card>
                  <v-card-text>웹툰 2</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="4">
                <v-card>
                  <v-card-text>웹툰 3</v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-card-title>전체 {{ currentDayName }}웹툰</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="2.4">
                <v-card>
                  <v-card-text>웹툰 1</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="2.4">
                <v-card>
                  <v-card-text>웹툰 2</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="2.4">
                <v-card>
                  <v-card-text>웹툰 3</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="2.4">
                <v-card>
                  <v-card-text>웹툰 4</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="2.4">
                <v-card>
                  <v-card-text>웹툰 5</v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>