<script setup lang="ts">

import {computed, onMounted, ref, watch} from "vue";
import {navigateToWebtoonEpisode} from "@/utils/navigation";
import {useWebtoonEpisodes} from "@/composables/useWebtoonEpisodes";
import {useWebtoon} from "@/composables/useWebtoons";

interface Props {
  webtoonId: string
  tab: string
}

const props = defineProps<Props>()

//TODO: webtoonId를 store에서 관리
const {data: webtoonData, updateWebtoonId: updateWebtoonId} = useWebtoon()
const {
  data: webtoonEpisodesData,
  updateWebtoonId: updateWebtoonEpisodesWebtoonId,
  updatePageNo: updateWebtoonEpisodesPageNo
} = useWebtoonEpisodes()

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

const totalCount = computed(() => webtoonEpisodesData.value?.totalCount ?? 0)
const episodes = computed(() => webtoonEpisodesData.value?.content ?? [])

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

function onClickEpisode(episodeId: number) {
  navigateToWebtoonEpisode(Number(props.webtoonId), episodeId, props.tab)
}

const page = ref(1)
const size = 10

watch(
    () => props.webtoonId,
    (newWebtoonId) => {
      if (newWebtoonId) {
        updateWebtoonEpisodesWebtoonId(Number(newWebtoonId))
        updateWebtoonId(Number(newWebtoonId))
      }
    },
    {immediate: true}
)

watch(() => page.value,
    (newPage) => {
      console.log("Updating page number to:", newPage)
      updateWebtoonEpisodesPageNo(newPage - 1)
    }
)

onMounted(() => {
  console.log('webtoonId:', props.webtoonId)
  console.log('tab:', props.tab)
})

</script>

<template>
  <v-card class="pa-3" elevation="0">
    <v-row no-gutters>
      <v-col cols="4" md="2" lg="2">
        <v-img
            :src="webtoonData?.thumbnail"
            class="rounded-lg"
            cover
        />
      </v-col>
      <v-col cols="8" md="10" lg="10" class="pl-0">
        <v-card-title class="font-weight-bold pt-0">{{ webtoonData?.title }}</v-card-title>
        <v-card-text class="pb-0">
          {{ webtoonData?.writer.name }} · 글 / {{ webtoonData?.illustrator.name }} · 그림
          <span v-if="webtoonData?.originalAuthor"> / {{ webtoonData?.originalAuthor?.name }} · 원작</span>
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
  <v-card>
    <!-- 광고 배너  -->
  </v-card>
  <v-card class="pa-4" elevation="0">
    <div class="d-flex justify-space-between align-center">
      <div class="entire-episode text-body-1 font-weight-bold text-grey-darken-1 pa-0 ma-0">
        총 {{ totalCount }}화
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
          v-for="episode in episodes"
          :key="episode.episodeId"
          class="pa-0 pt-1 pb-1"
          @click="onClickEpisode(episode.episodeId)"
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
              ★ {{ episode.rating }} &nbsp; {{ formatDate(episode.uploadDate) }}
            </div>
          </div>
        </v-row>
      </v-list-item>
    </v-list>

    <div v-if="episodes.length > 0" class="d-flex justify-center mt-4">
      <v-pagination
          v-model="page"
          :length="Math.ceil(totalCount / size)"
          total-visible="5"
          color="black"
      />
    </div>
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

::v-deep(.v-list-item:hover .text-body-1) {
  text-decoration: underline;
}
</style>