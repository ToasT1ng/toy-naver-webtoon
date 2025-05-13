<script setup lang="ts">

import {onMounted, ref} from "vue";

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

interface IEpisode {
  episodeId: number;
  title: string;
  thumbnail: string;
  rating: string;
  createdAt: string;
}

interface IEpisodes {
  webtoonId: number;
  pageNo: number;
  pageSize: number;
  totalCount: number;
  totalPage: number;
  isLastPage: boolean;
  episodes: IEpisode[];
}

const webtoonListData = ref<IEpisodes>({
  webtoonId: 123,
  pageNo: 0,
  pageSize: 10,
  totalCount: 100,
  totalPage: 10,
  isLastPage: false,
  episodes: [
    {
      episodeId: 2,
      title: '2화 : 다음',
      thumbnail: 'https://image-comic.pstatic.net/webtoon/796075/128/thumbnail_202x120_7c90ceae-294a-4bce-8f19-bd3dd8f86412.jpg',
      rating: '5.67',
      createdAt: '2023-10-02'
    },
    {
      episodeId: 1,
      title: '1화 : 시작',
      thumbnail: 'https://image-comic.pstatic.net/webtoon/796075/128/thumbnail_202x120_7c90ceae-294a-4bce-8f19-bd3dd8f86412.jpg',
      rating: '4.51',
      createdAt: '2023-10-01'
    }
  ]
})

function formatDate(dateString: string) {
  const date = new Date(dateString)
  const yy = String(date.getFullYear()).slice(2)
  const mm = String(date.getMonth() + 1).padStart(2, '0')
  const dd = String(date.getDate()).padStart(2, '0')
  return `${yy}.${mm}.${dd}`
}

type SortOption = 'asc' | 'dec'

const selectedSort = ref<SortOption>('asc')

const sortOptions = [
  {label: '최신화부터', value: 'dec'},
  {label: '1화부터', value: 'asc'},
] satisfies readonly { label: string; value: SortOption }[]

function onSort() {
  console.log(selectedSort.value) //TODO api 재호출
}

onMounted(() => {
  console.log(props.webtoonId) //TODO: 웹툰 상세 API 호출
})

</script>

<template>
  <v-card class="pa-3" elevation="0">
    <v-row no-gutters>
      <v-col cols="4" md="2" lg="2">
        <v-img
            :src="data.thumbnail"
            class="rounded-lg"
            cover
        />
      </v-col>
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
          <v-chip v-for="tag in data.tags" :key="tag" class="mr-2 text-grey-darken-2 pl-2 pr-2 font-weight-medium"
                  variant="tonal"
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
  <v-card>
    <!-- 광고 배너  -->
  </v-card>
  <v-card class="pa-4" elevation="0">
    <div class="d-flex justify-space-between align-center">
      <div class="entire-episode text-body-1 font-weight-bold text-grey-darken-1 pa-0 ma-0">
        총 {{ webtoonListData.episodes.length }}화
      </div>

      <v-btn-toggle
          v-model="selectedSort"
          color="black"
          variant="plain"
          class="pa-0 ma-0"
          style="height: 24px; line-height: 24px; min-height: 24px;"
          mandatory
      >
        <v-btn
            v-for="option in sortOptions"
            :key="option.value"
            :value="option.value"
            @click="onSort"
            small
            elevation="0"
            :ripple="false"
            class="pa-0"
        >
          {{ option.label }}
        </v-btn>
      </v-btn-toggle>
    </div>
    <v-list>
      <v-list-item
          v-for="episode in webtoonListData.episodes"
          :key="episode.episodeId"
          class="pa-0 pt-1 pb-1"
      >
        <v-row no-gutters align="center">
          <div style="width: 10%;" class="pt-1 pb-1 mr-4">
            <v-img
                class="with-border"
                width="100%"
                cover
                :src="episode.thumbnail"
            />
          </div>
          <div style="flex: 1;">
            <div class="font-weight-bold text-body-1">{{ episode.title }}</div>
            <div class="font-weight-bold text-grey text-body-2">
              ★ {{ episode.rating }} &nbsp; {{ formatDate(episode.createdAt) }}
            </div>
          </div>
        </v-row>
      </v-list-item>
    </v-list>
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

.with-border {
  border: 1px #ccc;
  border-radius: 3px;
}

.v-list-item {
  border-bottom: 1px solid #e0e0e0;
}

.v-list-item:first-child {
  border-top: 1px solid #e0e0e0;
}

::v-deep(.v-btn + .v-btn::before) {
  content: "";
  width: 1px;
  height: 12px;
  margin-left: 10px;
  margin-right: 10px;
  background-color: #e0e0e0;
  display: inline-block;
  vertical-align: middle;
}
</style>