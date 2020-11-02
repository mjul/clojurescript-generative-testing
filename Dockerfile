FROM node:14

RUN apt-get update \
        && apt-get -y install --no-install-recommends default-jdk-headless

RUN mkdir -p /home/node/app/node_modules && chown -R node:node /home/node/app
WORKDIR /home/node/app

# Initialise node packages
COPY package*.json ./

USER node
RUN npm install

# Initialise Shadow CLJS - this downloads its dependencies
COPY shadow-cljs.edn ./
RUN npx shadow-cljs info

# Now the source
COPY --chown=node:node src test ./

# Test server, Shadow-CLJS server and nREPL server:
EXPOSE 8021 9630 8777


CMD ["npx", "shadow-cljs", "watch", "test"]
