# ziro-lib

A collection of utilities primarily for my own use - Because I don't like copy pasta (I only like normal pasta).

## For Users

This is a library mod used by some of my mods. If none of my mods depend on it, you do not need to install
it (and can remove it if you have it).

## For Developers

This library exists for my own projects, so I generally will not add features unless I need them across multiple mods.

It should also be noted this library is written purely in Kotlin, with no _real_ regard for direct Java compatibility (unless required by i.e. mixins).

If you are looking for a more general-purpose utility library, I recommend checking out something
like [owo-lib](https://modrinth.com/project/ccKDOlHs) or [Collective](https://modrinth.com/project/e0M1UDsY), because
this is not intended to be that.

## Performance

There is currently no single entrypoint, so nothing is loaded or registered by default. Utilities are only used when
they are called directly by another project.
