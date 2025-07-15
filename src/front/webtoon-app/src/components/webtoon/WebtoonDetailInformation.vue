<script setup lang="ts">
import {useWebtoonStore} from "@/stores/webtoonStore";
import {useWebtoon} from "@/composables/useWebtoons";
import {computed, nextTick, ref, watch} from "vue";
import {updateWebtoonLike, useWebtoonLike} from "@/composables/useWebtoonLikes";

const webtoonStore = useWebtoonStore()

const {data: webtoonData, updateWebtoonId: updateWebtoonId} = useWebtoon()
const {data: webtoonLikeData, error: webtoonLikeError, updateWebtoonId: updateWebtoonLikeId} = useWebtoonLike()
const {mutateAsync: toggleLikeStatus, isPending: isLiking} = updateWebtoonLike()

const isTextAreaExpanded = ref(false)
const shouldShowToggleButton = ref(false)
const descRef = ref<HTMLElement | null>(null)
const tagRef = ref<HTMLElement | null>(null)

const convertedDescription = computed(() =>
    webtoonData.value?.description.replace(/\\n/g, '<br/>') ?? ''
)

const isLiked = computed(() => !!webtoonLikeData.value && webtoonLikeData.value.hello && !webtoonLikeError.value)

async function onClickLikeButton() {
  if (!webtoonData.value?.id) return
  const status = isLiked.value ? 'UNLIKE' : 'LIKE'
  try {
    await toggleLikeStatus({webtoonId: webtoonData.value.id, status})
    // updateWebtoonLikeId(webtoonData.value.id)
  } catch (e) {
    console.error('Toggle failed', e)
  }
}

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
        updateWebtoonLikeId(Number(newWebtoonId))
      }
    },
    {immediate: true}
)

watch(
    () => webtoonData?.value,
    () => {
      nextTick(() => {
        setTimeout(() => {
          const desc = descRef.value
          const tags = tagRef.value

          const descTooTall = desc instanceof HTMLElement && desc.scrollHeight > 140
          const tagsTooTall = tags instanceof HTMLElement && tags.scrollHeight > 32

          shouldShowToggleButton.value = descTooTall || tagsTooTall
        }, 0)
      })
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
          <span class="font-weight-semi-bold">{{ webtoonData?.writer.name }}</span> · 글 / <span
            class="font-weight-semi-bold">{{ webtoonData?.illustrator.name }}</span> · 그림
          <span v-if="webtoonData?.originalAuthor"> / <span
              class="font-weight-semi-bold">{{ webtoonData?.originalAuthor?.name }}</span> · 원작</span>
          ｜ {{ makDayOfWeekString(webtoonData?.dayOfWeek) }}웹툰 · {{ webtoonData?.restrictedAge }}세 이용가
        </v-card-text>
        <div class="description-wrapper mt-2">
          <v-card-text class="pt-2">
            <div
                ref="descRef"
                :class="['clamp-text', { clamped: !isTextAreaExpanded }]"
                v-html="convertedDescription"
            >
            </div>
          </v-card-text>

          <v-card-text class="pt-0 tag-container" :class="{ 'tag-clamp': !isTextAreaExpanded }">
            <div ref="tagRef">
              <v-chip
                  v-for="tag in webtoonData?.tags"
                  :key="tag"
                  class="mr-2 text-grey-darken-2 pl-2 pr-2 font-weight-medium"
                  variant="tonal"
                  color="grey-lighten-5"
                  rounded="sm"
                  density="compact"
                  small
              >
                #{{ tag }}
              </v-chip>
            </div>
          </v-card-text>

          <v-icon
              v-if="shouldShowToggleButton"
              class="toggle-icon text-grey"
              @click="isTextAreaExpanded = !isTextAreaExpanded"
          >
            {{ isTextAreaExpanded ? 'mdi-chevron-up' : 'mdi-chevron-down' }}
          </v-icon>
        </div>
      </v-col>
    </v-row>
    <v-row style="height: 80px">
      <v-col cols="5" class="mr-0 pr-0 h-100">
        <v-btn
            class="h-100 like-count-button"
            elevation="0"
            block
            :loading="isLiking"
            @click="onClickLikeButton"
        >
          {{ isLiked ? '관심해제' : '＋ 관심' }}
          {{ webtoonData?.likeCount?.toLocaleString() }}
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

.description-wrapper {
  position: relative;
}

.clamp-text {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 6;
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 1.5;
  font-size: 14px;
  color: #333;
  white-space: normal;
}

.clamp-text:not(.clamped) {
  -webkit-line-clamp: unset;
  display: block;
}

.tag-clamp {
  max-height: 32px;
  overflow: hidden;
  white-space: nowrap;
}

.toggle-icon {
  position: absolute;
  top: 4px;
  right: 4px;
  z-index: 1;
  font-size: 28px;
  cursor: pointer;
  background: none !important;
  box-shadow: none !important;
  outline: none !important;
  border: none !important;
  user-select: none;
}

.toggle-icon:hover,
.toggle-icon:focus,
.toggle-icon:active {
  background: none !important;
  box-shadow: none !important;
  outline: none !important;
  opacity: 1 !important;
}
</style>