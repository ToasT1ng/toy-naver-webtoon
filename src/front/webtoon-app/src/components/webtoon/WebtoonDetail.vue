<script setup lang="ts">

import {computed, onMounted, ref} from "vue";
import {da} from "vuetify/locale";

const props = defineProps({
  webtoonId: {
    type: String,
    required: true
  },
  tab: {
    type: String,
    required: true
  }
})

interface IPerson {
  personId: number;
  name: string;
}

interface IWebtoonDetail {
  webtoonId: number;
  title: string;
  writer: IPerson
  illustrator: IPerson
  originalAuthor?: IPerson
  dayOfWeek: string;
  restrictedAge: number;
  description: string;
  tags: string[];
  thumbnail: string;
  likeCount: number;
}

const data = ref<IWebtoonDetail>({
  webtoonId: 123,
  title: '웹툰 제목',
  writer: {
    personId: 1,
    name: '작가이름'
  },
  illustrator: {
    personId: 2,
    name: '일러이름'
  },
  originalAuthor: {
    personId: 3,
    name: '원작자이름'
  },
  dayOfWeek: 'mon',
  restrictedAge: 19,
  description: '웹툰 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명  엄청 긴 설명 그랬다고 한다.\n 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 그랬는데...\n 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 엄청 긴 설명 그렇게 됐다.',
  tags: ['액션', '판타지'],
  thumbnail: 'https://image-comic.pstatic.net/webtoon/796075/thumbnail/thumbnail_IMAG21_31f75c4c-81c9-454a-8d92-9e23b577e1a5.jpg',
  likeCount: 1000
})

const daysOfWeek = [
  {name: '월요', value: 'mon'},
  {name: '화요', value: 'tue'},
  {name: '수요', value: 'wed'},
  {name: '목요', value: 'thu'},
  {name: '금요', value: 'fri'},
  {name: '토요', value: 'sat'},
  {name: '일요', value: 'sun'}
]

function getDayOfWeek(inputDay: string) {
  const dayOfWeek = daysOfWeek.find(day => day.value === inputDay)
  return dayOfWeek ? dayOfWeek.name : ''
}

onMounted(() => {
  console.log(props.webtoonId) //TODO: 웹툰 상세 API 호출
})

</script>

<template>
  <v-card class="pa-3" elevation="0">
    <v-row no-gutters>
      <!-- 썸네일 영역 -->
      <v-col cols="4" md="2" lg="2">
        <v-img
            :src="data.thumbnail"
            class="rounded-lg"
            cover
        />
      </v-col>

      <!-- 텍스트 정보 영역 -->
      <v-col cols="8" md="10" lg="10" class="pl-0">
        <v-card-title class="font-weight-bold pt-0">{{ data.title }}</v-card-title>
        <v-card-text class="pb-0">
          {{ data.writer.name }} · 글 / {{ data.illustrator.name }} · 그림
          <span v-if="data.originalAuthor"> / {{ data.originalAuthor?.name }} · 원작</span>
          ｜ {{ getDayOfWeek(data.dayOfWeek) }}웹툰 · {{ data.restrictedAge }}세 이용가
        </v-card-text>
        <v-card-text class="pt-2" style="white-space: pre-line;">
          {{ data.description }}
        </v-card-text>
        <v-card-text class="pt-0">
          <v-chip v-for="tag in data.tags" :key="tag" class="mr-2 text-grey-darken-2 pl-2 pr-2 font-weight-medium" variant="tonal"
                  color="grey-lighten-5" rounded="sm" density="compact" small>
            #{{ tag }}
          </v-chip>
        </v-card-text>
      </v-col>
    </v-row>

    <v-row style="height: 80px">
      <v-col cols="5" class="mr-0 pr-0 h-100">
        <v-btn class="h-100 like-count-button" elevation="0" block>＋ 관심 {{ data.likeCount.toLocaleString() }}</v-btn>
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