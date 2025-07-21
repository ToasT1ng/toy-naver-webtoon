<script setup lang="ts">
import {useWebtoonLikes} from "@/composables/useWebtoonLikes";
import {computed, ref} from "vue";
import {TLikeWebtoonsSortOption} from "@/features/webtoon/types/sortOptions";
import {navigateToWebtoonDetail} from "@/utils/navigation";

const {data: webtoonLikes} = useWebtoonLikes()

const totalCount = computed(() => webtoonLikes.value?.result.length)

const selectedSort = ref<TLikeWebtoonsSortOption>('UPLOADED')

const sortOptions = [
  {label: '업데이트순', value: 'UPLOADED'},
  {label: '최근등록순', value: 'LIKED_AT'},
] satisfies readonly { label: string; value: TLikeWebtoonsSortOption }[]

function onSort() {
  console.log("Sorting by:", selectedSort.value);
}

function formatDate(ts: number): string {
  const d = new Date(ts)
  const yy = d.getFullYear().toString().slice(-2)
  const mm = String(d.getMonth() + 1).padStart(2, '0')
  const dd = String(d.getDate()).padStart(2, '0')
  return `${yy}.${mm}.${dd}`
}

</script>

<template>
  <v-container fluid class="pt-1 px-0">
    <v-row>
      <v-col class="mt-2" cols="12">
        <v-card elevation="0">
          <v-card-title class="d-flex align-center">
            <div class="font-weight-bold me-2">관심웹툰</div>

            <div class="d-flex align-center ml-2 mt-2 text-body-2 font-weight-bold text-grey-darken-1">
              <span class="me-2">전체 {{ totalCount ?? 0 }}</span>
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
                    class="pa-0 mx-1"
                    style="min-width: auto;"
                >
                  {{ option.label }}
                </v-btn>
              </v-btn-toggle>
            </div>
          </v-card-title>
          <v-card-item class="mt-2">
            <v-row>
              <v-col
                  v-for="webtoon in webtoonLikes?.result"
                  :key="webtoon.webtoonId"
                  cols="6"
                  style="border-bottom: 1px solid #e0e0e0;"
              >
                <v-card elevation="0">
                  <v-card-text class="pa-0">
                    <div class="d-flex align-center justify-space-between">
                      <div>
                        <v-img
                            :src="webtoon.thumbnailUrl"
                            width="120"
                            height="120"
                            cover
                            class="ma-0 mr-1 with-border"
                        ></v-img>
                      </div>

                      <div class="flex-grow-1 px-4">
                        <div class="font-weight-bold text-body-1">
                          {{ webtoon.title }}
                        </div>
                        <div class="font-weight-bold text-body-2 text-grey">
                          작가
                        </div>
                      </div>

                      <div class="font-weight-bold text-body-1 text-grey text-right mr-5" style="white-space: nowrap;">
                        {{ formatDate(webtoon.likedAt) }}
                      </div>
                    </div>
                  </v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-card-item>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.with-border {
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>