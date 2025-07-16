import {computed, ref} from "vue";
import {useMutation, useQuery, useQueryClient} from "@tanstack/vue-query";
import {extractValidData} from "@/utils/extractValidData";
import {getUserLikedWebtoon, getUserLikedWebtoons, updateUserLikedWebtoon} from "@/api/webtoonLikes";
import {TWebtoonLikedRequestStatus} from "@/features/webtoonLikes/types/webtoonLikedRequestStatus";

export const useWebtoonLikes = () => {
    const query = useQuery({
        queryKey: computed(() => ['webtoonLikes']),
        queryFn: async () => {
            const result = await getUserLikedWebtoons()
            return extractValidData(result)
        },
        enabled: true,
        staleTime: 1000 * 10,
        retry: 0,
    })

    return {
        ...query,
    }
}

export const useWebtoonLike = () => {
    const webtoonId = ref<number | undefined>(undefined)
    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }
    const query = useQuery({
        queryKey: computed(() => ['webtoonLike', webtoonId]),
        queryFn: async () => {
            if (!webtoonId.value) {
                return Promise.reject(new Error('webtoonId is required'))
            }
            const result = await getUserLikedWebtoon({webtoonId: webtoonId.value})
            return extractValidData(result)
        },
        enabled: true,
        staleTime: 1000 * 10,
        retry: 0,
    })

    return {
        updateWebtoonId,
        ...query,
    }
}

export const updateWebtoonLike = () => {
    const queryClient = useQueryClient()
    return useMutation({
        mutationFn: async ({ webtoonId, status }: { webtoonId: number, status: TWebtoonLikedRequestStatus }) => {
            const result = await updateUserLikedWebtoon({
                webtoonId: webtoonId,
                status: status,
            })
            return extractValidData(result)
        },
        onSuccess: (_data, variables) => {
            queryClient.invalidateQueries({
                queryKey: ['webtoonLike', variables.webtoonId],
            })
        },
    })
}