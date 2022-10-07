FROM gradle:latest

# Allow application key and secret to get passed in as build arguments.
# If you don't want to include your API key and secret with each build,
# you can also set their value statically below, meaning that their values
# won't need to be supplied when a new image is built in the future.
ARG app_key=YOUR_APP_KEY
ARG app_secret=YOUR_APP_SECRET

# Set environment variables, based on the build arguments.
ENV DWOLLA_APP_KEY=$app_key
ENV DWOLLA_APP_SECRET=$app_secret

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle compileKotlin compileTestKotlin --no-daemon
ENTRYPOINT ["gradle"]