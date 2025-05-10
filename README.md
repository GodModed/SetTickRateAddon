# SkriptClientTickRate

SkriptClientTickRate is a Minecraft plugin that allows you to set the tick rate of a player and optionally freeze or unfreeze their animations using Skript.

## Features

- Set the tick rate of a player.
- Freeze or unfreeze player animations.

## Requirements

- Minecraft server running Paper `1.20.3 - Latest`.
- Skript plugin installed on the server.

## Installation

1. Download the latest release of SkriptClientTickRate.
2. Place the downloaded JAR file in the `plugins` directory of your Minecraft server.
3. Restart the server to load the plugin.

## Usage

To use the plugin, you need to create a Skript that utilizes the custom effect provided by SkriptClientTickRate.

### Example Skript

```sk
command /settickrate <player> <number>:
    trigger:
        set tick rate of arg-1 to arg-2 and freeze their animations
```

This command allows you to set the tick rate of a specified player and optionally freeze or unfreeze their animations.

## Building from Source

To build SkriptClientTickRate from source, you need to have Maven installed.

1. Clone the repository:
    ```sh
    git clone https://github.com/GodModed/SkriptClientTickRate.git
    cd SkriptClientTickRate
    ```

2. Build the project using Maven:
    ```sh
    mvn clean package
    ```

3. The built JAR file will be located in the `target` directory.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request on GitHub.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
