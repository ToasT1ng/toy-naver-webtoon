import apiClient, {ICommonResponse} from "@/api/index";
import {TWebtoonLikedRequestStatus} from "@/features/webtoonLikes/types/webtoonLikedRequestStatus";

interface IGetUserLikedWebtoonsResponse {
    result: IGetUserLikedWebtoonResponse[];
}

export const getUserLikedWebtoons = async (): Promise<ICommonResponse<IGetUserLikedWebtoonsResponse>> => {
    const response = await apiClient.get(`/users/webtoon-likes`)
    return response.data
}

interface IGetUserLikedWebtoonRequest {
    webtoonId: number;
}

interface IGetUserLikedWebtoonResponse {
    webtoonId: number;
    title: string;
    thumbnailUrl: string;
    likedAt: number;
}

export const getUserLikedWebtoon = async (
    req: IGetUserLikedWebtoonRequest
): Promise<ICommonResponse<IGetUserLikedWebtoonResponse>> => {
    const response = await apiClient.get(`/users/webtoon-likes/${req.webtoonId}`)
    return response.data
}

interface IUpdateUserLikedWebtoonRequest {
    webtoonId: number;
    status: TWebtoonLikedRequestStatus
}

export const updateUserLikedWebtoon = async (
    req: IUpdateUserLikedWebtoonRequest
): Promise<ICommonResponse<IGetUserLikedWebtoonResponse>> => {
    const response = await apiClient.post(`/users/webtoon-likes/${req.webtoonId}`, {
        status: req.status,
    })
    return response.data
}

