import apiClient, {ICommonResponse} from "@/api/index";

interface IPagingWebtoonEpisodeCommentsRequest {
    webtoonId: number
    episodeId: number
    pageNo: number
    pageSize: number
}

interface IPagingWebtoonEpisodeCommentsResponse {
    content: IWebtoonEpisodeCommentResponse[];
}

interface IWebtoonEpisodeCommentResponse {
    id: number,
    parentId: number
    episodeId: number
    user: IWebtoonEpisodeCommentUserResponse
    content: string,
    createdAt: number,
    updatedAt: number
}

interface IWebtoonEpisodeCommentUserResponse {
    id: number,
    username: string,
    profileImageUrl?: string
}

export const getPagingWebtoonEpisodeComments = async (
    req: IPagingWebtoonEpisodeCommentsRequest
): Promise<ICommonResponse<IPagingWebtoonEpisodeCommentsResponse>> => {
    const response = await apiClient.get<ICommonResponse<IPagingWebtoonEpisodeCommentsResponse>>(`/webtoons/${req.webtoonId}/episodes/${req.episodeId}/comments`, {
        params: {
            pageNo: req.pageNo,
            pageSize: req.pageSize,
        },
    })
    return response.data
}