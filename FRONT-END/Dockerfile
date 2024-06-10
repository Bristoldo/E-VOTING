# Dockerfile for React Native Expo frontend
FROM node:14

# Set working directory
WORKDIR /app

# Install expo-cli globally
RUN npm install -g expo-cli

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application
COPY . .

# Start the application
CMD ["expo", "start", "--web"]

