import {computed, ref} from 'vue'
import {useQuery} from '@tanstack/vue-query'
import {getPagingWebtoonEpisodes, getWebtoonEpisode, getWebtoonEpisodeNavigation} from "@/api/webtoonEpisode";
import {SortDirection} from "@/types/common";

//TODO webtoonId를 store에서 가져오도록 변경?

export const useWebtoonEpisode = () => {
    const webtoonId = ref<number | undefined>(undefined)
    const episodeId = ref<number | undefined>(undefined)
    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }
    function updateEpisodeId(newEpisodeId: number){
        episodeId.value = newEpisodeId
    }
    const query = useQuery({
        queryKey: computed(() => ['webtoonEpisode', webtoonId.value, episodeId.value]),
        queryFn: async () => {
            if (!webtoonId.value || !episodeId.value) {
                return Promise.reject(new Error('webtoonId and episodeId are required'))
            }
            return await getWebtoonEpisode({
                webtoonId: webtoonId.value,
                episodeId: episodeId.value
            })
        },
        enabled: () => !!webtoonId.value || !!episodeId.value,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        updateEpisodeId,
        ...query,
    }
}

export const useWebtoonEpisodes = () => {
    const pageNo = ref(1)
    const pageSize = ref(10)
    const webtoonId = ref<number | undefined>(undefined)
    const sortDirection = ref<SortDirection>('DESC')

    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }

    function updatePageNo(newPageNo: number){
        pageNo.value = newPageNo
    }

    function updateSortDirection(newSortDirection: SortDirection){
        sortDirection.value = newSortDirection
    }

    const query = useQuery({
        queryKey: computed(() => ['webtoonEpisodes', webtoonId.value, pageNo.value, sortDirection.value]),
        queryFn: async () => {
            if (!webtoonId.value) {
                return Promise.reject(new Error('webtoonId is required'))
            }
            return await getPagingWebtoonEpisodes({
                pageNo: pageNo.value,
                pageSize: pageSize.value,
                webtoonId: webtoonId.value,
                sortDirection: sortDirection.value,
            })
        },
        enabled: () => !!webtoonId.value,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        updatePageNo,
        updateSortDirection,
        ...query,
    }
}

export const useWebtoonEpisodeNavigation = () => {
    const webtoonId = ref<number | undefined>(undefined)
    const episodeId = ref<number | undefined>(undefined)
    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }

    function updateEpisodeId(newEpisodeId: number){
        episodeId.value = newEpisodeId
    }
    const query = useQuery({
        queryKey: computed(() => ['webtoonEpisodeNavigation', webtoonId.value, episodeId.value]),
        queryFn: async () => {
            if (!webtoonId.value || !episodeId.value) {
                return Promise.reject(new Error('webtoonId and episodeId are required'))
            }
            return await getWebtoonEpisodeNavigation({
                webtoonId: webtoonId.value,
                episodeId: episodeId.value
            })
        },
        enabled: () => !!webtoonId.value || !!episodeId.value,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        updateEpisodeId,
        ...query,
    }
}