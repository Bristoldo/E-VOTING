version: '3.8'

services:
  frontend:
    build:
      context: ./FRONT-END
      dockerfile: Dockerfile
    ports:
      - "3000:3000"
    networks:
      - app-network

  backend:
    build:
      context: ./BACK-END
      dockerfile: Dockerfile
    ports:
      - "8081:8081"
    networks:
      - app-network

networks:
  app-network:
    driver: bridge