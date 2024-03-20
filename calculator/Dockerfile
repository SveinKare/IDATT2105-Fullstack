FROM node:latest

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy package.json and package-lock.json (if available)
COPY package*.json ./

# Step 4: Install project dependencies
RUN npm install

# Step 5: Copy the rest of your Vue.js application source code
COPY . .

# Step 6: Expose the port the app runs on
EXPOSE 5173

# Step 7: Run the application in development mode
CMD ["npm", "run", "dev"]
