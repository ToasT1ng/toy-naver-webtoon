<script setup lang="ts">
import {computed, ref, watch} from "vue";
import {navigateToWebtoonEpisode} from "@/utils/navigation";
import {useWebtoonEpisodes} from "@/composables/useWebtoonEpisodes";
import {useWebtoonStore} from "@/stores/webtoonStore";

const webtoonStore = useWebtoonStore()

const {
  data: webtoonEpisodesData,
  updateWebtoonId: updateWebtoonEpisodesWebtoonId,
  updatePageNo: updateWebtoonEpisodesPageNo
} = useWebtoonEpisodes()

const totalCount = computed(() => webtoonEpisodesData.value?.totalCount ?? 0)
const episodes = computed(() => webtoonEpisodesData.value?.content ?? [])

const page = ref(1)
const size = 10

type SortOption = 'asc' | 'dec'

const selectedSort = ref<SortOption>('asc')

const sortOptions = [
  {label: '최신화부터', value: 'dec'},
  {label: '1화부터', value: 'asc'},
] satisfies readonly { label: string; value: SortOption }[]

function formatDate(dateString: string) {
  const date = new Date(dateString)
  const yy = String(date.getFullYear()).slice(2)
  const mm = String(date.getMonth() + 1).padStart(2, '0')
  const dd = String(date.getDate()).padStart(2, '0')
  return `${yy}.${mm}.${dd}`
}

function onSort() {
  console.log(selectedSort.value) //TODO api 재호출
}

function onClickEpisode(episodeId: number) {
  navigateToWebtoonEpisode(webtoonStore.webtoonId, episodeId, webtoonStore.tab)
}

watch(
    () => webtoonStore.webtoonId,
    (newWebtoonId) => {
      if (newWebtoonId) {
        updateWebtoonEpisodesWebtoonId(Number(newWebtoonId))
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
</script>
<template>
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