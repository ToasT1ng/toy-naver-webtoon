import {defineStore} from 'pinia'
import {ref} from 'vue'
import {WeekPageTab} from "@/types/common";

export const useWebtoonStore = defineStore('webtoon', () => {
    const webtoonId = ref<number | null>(null)
    const tab = ref<WeekPageTab>('all')

    function setWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }

    function setTab(newTab: WeekPageTab) {
        tab.value = newTab
    }

    return {
        webtoonId,
        tab,
        setWebtoonId,
        setTab
    }
})