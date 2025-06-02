import apiClient, {ICommonResponse} from './index'
import {SortDirection} from "@/types/common";

interface IPagingWebtoonEpisodesRequest {
    webtoonId: number
    pageNo: number
    pageSize: number
    sortDirection: SortDirection
}

interface IPagingWebtoonEpisodesResponse {
    webtoonId: number;
    pageNo: number;
    pageSize: number;
    totalCount: number;
    totalPage: number;
    isLastPage: boolean;
    content: IWebtoonEpisodeResponse[];
}

export const getPagingWebtoonEpisodes = async (
    req: IPagingWebtoonEpisodesRequest
): Promise<IPagingWebtoonEpisodesResponse> => {
    const response = await apiClient.get<IPagingWebtoonEpisodesResponse>(`/webtoons/${req.webtoonId}/episodes`, {
        params: {
            pageNo: req.pageNo,
            pageSize: req.pageSize,
            sortDirection: req.sortDirection,
        },
    })
    return response.data
}

interface IWebtoonEpisodeRequest {
    webtoonId: number
    episodeId: number
}

interface IWebtoonEpisodeResponse {
    webtoonTitle: string;
    webtoonId: number;
    episodeId: number;
    title: string;
    thumbnail: string;
    rating: string;
    uploadDate: string;
    images: string[];
}

export const getWebtoonEpisode = async (
    req: IWebtoonEpisodeRequest
): Promise<ICommonResponse<IWebtoonEpisodeResponse>> => {
    const response = await apiClient.get<ICommonResponse<IWebtoonEpisodeResponse>>(`/webtoons/${req.webtoonId}/episodes/${req.episodeId}`)
    return response.data
}

interface IWebtoonEpisodeNavigationRequest {
    webtoonId: number
    episodeId: number
}

interface IWebtoonEpisodeNavigationResponse {
    previousEpisodeId: number
    currentEpisodeId: number
    nextEpisodeId: number
}

export const getWebtoonEpisodeNavigation = async (
    req: IWebtoonEpisodeNavigationRequest
): Promise<ICommonResponse<IWebtoonEpisodeNavigationResponse>> => {
    const response = await apiClient.get<ICommonResponse<IWebtoonEpisodeNavigationResponse>>(`/webtoons/${req.webtoonId}/episodes/${req.episodeId}/navigation`)
    return response.data
}