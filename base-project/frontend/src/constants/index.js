export const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080/api";
export const BASE_URL = "http://localhost:8080";
export const ACCESS_TOKEN = 'accessToken';
export const USER_ID = "userId";
export const OAUTH2_REDIRECT_URI = process.env.REACT_APP_OAUTH2_REDIRECT_URI || 'http://localhost:3000/oauth2/redirect';
export const GOOGLE_AUTH_URL = BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = BASE_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
