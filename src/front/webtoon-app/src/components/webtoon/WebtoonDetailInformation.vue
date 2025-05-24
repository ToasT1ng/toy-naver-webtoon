<script setup lang="ts" >
import {useWebtoonStore} from "@/stores/webtoonStore";
import {useWebtoon} from "@/composables/useWebtoons";
import {watch} from "vue";

const webtoonStore = useWebtoonStore()

const {data: webtoonData, updateWebtoonId: updateWebtoonId} = useWebtoon()

const daysOfWeek = [
  {name: '월요', value: 'mon'},
  {name: '화요', value: 'tue'},
  {name: '수요', value: 'wed'},
  {name: '목요', value: 'thu'},
  {name: '금요', value: 'fri'},
  {name: '토요', value: 'sat'},
  {name: '일요', value: 'sun'}
]

function makDayOfWeekString(inputDay: string) {
  const dayOfWeek = daysOfWeek.find(day => day.value === inputDay)
  return dayOfWeek ? dayOfWeek.name : ''
}

watch(
    () => webtoonStore.webtoonId,
    (newWebtoonId) => {
      if (newWebtoonId) {
        updateWebtoonId(Number(newWebtoonId))
      }
    },
    {immediate: true}
)

</script>
<template>
  <v-card class="pa-3" elevation="0">
    <v-row no-gutters>
      <v-col cols="4" md="2" lg="2">
        <v-img
            :src="webtoonData?.thumbnail"
            class="rounded-lg"
            cover
            style="height: 280px"
        />
      </v-col>
      <v-col cols="8" md="10" lg="10" class="pl-0">
        <v-card-title class="font-weight-extra-bold pt-0">{{ webtoonData?.title }}</v-card-title>
        <v-card-text class="pb-0">
          <span class="font-weight-semi-bold">{{ webtoonData?.writer.name }}</span> · 글 / <span class="font-weight-semi-bold">{{ webtoonData?.illustrator.name }}</span> · 그림
          <span v-if="webtoonData?.originalAuthor"> / <span class="font-weight-semi-bold">{{ webtoonData?.originalAuthor?.name }}</span> · 원작</span>
          ｜ {{ makDayOfWeekString(webtoonData?.dayOfWeek) }}웹툰 · {{ webtoonData?.restrictedAge }}세 이용가
        </v-card-text>
        <v-card-text class="pt-2" style="white-space: pre-line;">
          {{ webtoonData?.description }}
        </v-card-text>
        <v-card-text class="pt-0">
          <v-chip v-for="tag in webtoonData?.tags" :key="tag"
                  class="mr-2 text-grey-darken-2 pl-2 pr-2 font-weight-medium"
                  variant="tonal"
                  color="grey-lighten-5" rounded="sm" density="compact" small>
            #{{ tag }}
          </v-chip>
        </v-card-text>
      </v-col>
    </v-row>
    <v-row style="height: 80px">
      <v-col cols="5" class="mr-0 pr-0 h-100">
        <v-btn class="h-100 like-count-button" elevation="0" block>＋ 관심 {{
          webtoonData?.likeCount?.toLocaleString()
          }}
        </v-btn>
      </v-col>
      <v-col cols="5" class="mr-0 pr-0 h-100">
        <v-btn class="h-100 other-button" elevation="0" block>최근 본 · N화 : 제목이름</v-btn>
      </v-col>
      <v-col cols="2">
        <v-btn class="h-100 other-button" elevation="0" block>공유하기</v-btn>
      </v-col>
    </v-row>
  </v-card>
</template>
<style scoped>
.like-count-button {
  background-color: #f0f0f0 !important;
  color: black;
}

.other-button {
  background-color: #f1f0f0 !important;
  color: black;
}

</style>