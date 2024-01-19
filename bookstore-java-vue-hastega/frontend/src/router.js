import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('./pages/AppLogin.vue'),
        },
        {
            path: '/books/user/:id',
            name: 'book-list',
            component: () => import('./pages/AppBook.vue'),
        },
        {
            path: '/books/:id',
            name: 'book-detail',
            component: () => import('./pages/AppBookDetail.vue'),
        },
    ]
});

export { router };