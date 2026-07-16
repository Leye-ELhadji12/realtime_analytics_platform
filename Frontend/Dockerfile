FROM node:24-alpine AS build
WORKDIR /app
# Install dependencies
COPY package*.json ./
RUN npm ci

# Copy source code and build
COPY . .
RUN npm run build

# Use Nginx to serve the built static files
FROM nginx:alpine
# Angular 17+ uses the 'browser' subfolder for client-side assets
COPY --from=build /app/dist/Frontend/browser /usr/share/nginx/html

# Configure Nginx for a Single Page Application (SPA)
RUN echo 'server { \
    listen 80; \
    location / { \
        root   /usr/share/nginx/html; \
        index  index.html index.htm; \
        try_files $uri $uri/ /index.html; \
    } \
}' > /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
