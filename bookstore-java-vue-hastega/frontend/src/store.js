import { reactive } from "vue";

export const store = reactive({
    pathApiUsers: "http://localhost:8080/api/v1/users",
    pathApiBooks: "http://localhost:8080/api/v1/books/user/",
    pathApiBookDetail: "http://localhost:8080/api/v1/books/",
    userIdLogin: 0,
    arrayUsers: [],
    loading: true,
});